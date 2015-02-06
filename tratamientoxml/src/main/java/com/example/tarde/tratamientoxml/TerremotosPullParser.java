package com.example.tarde.tratamientoxml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tarde on 06/02/2015.
 */
public class TerremotosPullParser {

    public static List<Terremoto> parse(InputStream is) throws XmlPullParserException, IOException, ParseException {
        LinkedList<Terremoto> resultado = new LinkedList<>();

        XmlPullParser xmlPullParser = Xml.newPullParser();

        xmlPullParser.setInput(is,"UTF-8");

        int event = xmlPullParser.getEventType();

        Terremoto terremoto = null;

        while(event != XmlPullParser.END_DOCUMENT){
            String tag = xmlPullParser.getName();

            if (event == XmlPullParser.START_TAG) {
                if (tag.equals("entry")) {
                    terremoto = new Terremoto();
                } else if (tag.equals("id") && terremoto != null) {
                    terremoto.setId(xmlPullParser.nextText());
                } else if (tag.equals("title") && terremoto != null) {
                    String titulo = xmlPullParser.nextText();
                    terremoto.setTitulo(titulo);
                    terremoto.setMagnitud(Float.valueOf(titulo.split(" ")[1]));
                } else if (tag.equals("updated") && terremoto != null) {
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    Date date = dateFormat.parse(xmlPullParser.nextText());
                    terremoto.setFecha(date);
                } else if (tag.equals("link") && terremoto != null) {
                    terremoto.setLink(xmlPullParser.getAttributeValue(null, "href"));
                } else if (tag.equals("point") && terremoto != null) {
                    String[] latlon = xmlPullParser.nextText().split(" ");
                    terremoto.setLatitud(Float.valueOf(latlon[0]));
                    terremoto.setLongitud(Float.valueOf(latlon[1]));
                }
            } else if (event == XmlPullParser.END_TAG){
                if (tag.equals("entry")) {
                    resultado.add(terremoto);
                    terremoto = null;
                }
            }
            event = xmlPullParser.next();
        }

        return resultado;
    }
}
