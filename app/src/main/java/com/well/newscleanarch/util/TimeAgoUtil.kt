package com.well.newscleanarch.util

import org.joda.time.DateTime
import org.joda.time.Duration
import java.text.ParseException

object TimeAgoUtil {

    fun getTimeAgoDate(dateString: String): String {
        val date = dateString.iso8601toDateTime()
        val dateNow = DateTime.now()
        val duration = Duration(date, dateNow)
        return when {
            duration.standardSeconds < 60 -> "${duration.standardSeconds} seconds ago"
            duration.standardMinutes < 2 -> "1 minute ago"
            duration.standardMinutes < 60 -> "${duration.standardMinutes} minutes ago"
            duration.standardHours == 1L -> "1 hour ago"
            duration.standardHours < 24 -> "${duration.standardHours} hours ago"
            duration.standardDays == 1L -> "1 day ago"
            duration.standardDays < 30 -> "${duration.standardDays} days ago"
            duration.standardDays < 60 -> "1 month ago"
            duration.standardDays < 365 -> "${duration.standardDays / 30} months ago"
            duration.standardDays < 730 -> "1 year ago"
            duration.standardDays > 365 -> "${duration.standardDays / 365} years ago"
            else -> ""
        }
    }

    private fun String.iso8601toDateTime(): DateTime {
        return try {
            if (this.isNotEmpty()) {
                DateTime(this)
            } else {
                DateTime.now()
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            DateTime.now()
        }
    }
}