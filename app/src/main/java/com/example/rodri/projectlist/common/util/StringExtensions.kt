package com.example.rodri.projectlist.common.util

import com.example.rodri.projectlist.common.GlobalConstants

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


fun Date.parseDDMMYYYDate(): String {
    val sym = DateFormatSymbols.getInstance()
    return SimpleDateFormat(GlobalConstants.DATE_FORMAT_DATA, sym).format(this)
}
