package com.example.rodri.projectlist.common.rest.service

import com.example.rodri.projectlist.common.GlobalConstants
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import timber.log.Timber
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {
    override fun deserialize(jsonElement: JsonElement, typeOF: Type, context: JsonDeserializationContext): Date? {
        for (format in DATE_FORMATS) {
            try {
                return SimpleDateFormat(format).parse(jsonElement.asString)
            } catch (e: ParseException) {
                Timber.e(
                    "Can't parse date: ${jsonElement.asString} to [$format] format"
                )
            }
        }
        Timber.e("Unparseable date: ${jsonElement.asString}. Supported formats: ${Arrays.toString(DATE_FORMATS)}")
        return null
    }

    companion object {
        private val DATE_FORMATS = arrayOf(GlobalConstants.DATE_FORMAT_BODY)
    }
}