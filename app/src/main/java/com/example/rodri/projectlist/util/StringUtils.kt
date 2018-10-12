package com.example.rodri.projectlist.util

import com.example.rodri.projectlist.GlobalConstants

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


object StringUtils {
    fun parseDDMMYYYDate(date: Date): String {
        val sym = DateFormatSymbols.getInstance()
        return SimpleDateFormat(GlobalConstants.DATE_FORMAT_DATA, sym).format(date)
    }
}