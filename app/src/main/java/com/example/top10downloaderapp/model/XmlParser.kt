package com.example.top10downloaderapp.model

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.net.URL

class XmlParser {

    private val FEED_URL =
        "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml"
    private var applications = arrayListOf<Application>()
    private var text = ""

    private var title = ""
    private var image = ""

    fun parse(): ArrayList<Application> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val parser = factory.newPullParser()
            val url = URL(FEED_URL)
            parser.setInput(url.openStream(), null)
            var evenType = parser.eventType
            while (evenType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (evenType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", true) -> {
                            title = text
                        }
                        tagName.equals("im:image", true) -> {
                            image = text
                            if (image[image.length - 8] == '0') {
                                applications.add(Application(title, image))
                            }
                        }
                        else -> {}
                    }
                    else -> {}
                }
                evenType = parser.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return applications
    }


}