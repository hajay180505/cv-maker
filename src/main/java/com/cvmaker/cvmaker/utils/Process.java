package com.cvmaker.cvmaker.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Process.
 */
public class Process {
    /**
     * Process string.
     *
     * @param strings the strings
     * @return the string
     */
    public static String process(List<String> strings){
        StringBuilder ans = new StringBuilder();
        for( int i = 0; i < strings.size(); i++){
            if( i == strings.size()-1){
               return  ans.append(strings.get(i)).toString();
            }
            ans.append(strings.get(i)).append(", ");
        }
        return ans.toString();
    }

    /**
     * Process mobile string.
     *
     * @param string the string
     * @return the string
     */
    public static String processMobile(String string){
        return "+91-"+string.substring(0,5) + "-" + string.substring(5,10);
    }

    /**
     * Process date paragraph.
     *
     * @param string the string
     * @param font   the font
     * @return the paragraph
     */
    static Paragraph processDate(String string, PdfFont font){
        String[] tok = string.split("-");
        if(tok[2].startsWith("0")){
            tok[2] = String.valueOf(tok[2].charAt(1));
        }
        Text st = new Text("st").setFont(font).setTextRise(7).setFontSize(6);
        Text nd = new Text("nd").setFont(font).setTextRise(7).setFontSize(6);
        Text rd = new Text("rd").setFont(font).setTextRise(7).setFontSize(6);
        Text th = new Text("th").setFont(font).setTextRise(7).setFontSize(6);

        switch (tok[1]){
            case "01" : tok[1] = " January ";
            break;
            case "02" : tok[1] = " February ";
            break;
            case "03" : tok[1] = " March ";
            break;
            case "04" : tok[1] = " April ";
            break;
            case "05" : tok[1] = " May ";
            break;
            case "06" : tok[1] = " June ";
            break;
            case "07" : tok[1] = " July ";
            break;
            case "08" : tok[1] = " August ";
            break;
            case "09" : tok[1] = " September ";
            break;
            case "10" : tok[1] = " October ";
            break;
            case "11" : tok[1] = " November ";
            break;
            case "12" : tok[1] = " December ";
            break;

        }

        switch (tok[2]) {
            case "1":
                return new Paragraph(new Text("1")).add(st).add(tok[1]).add(tok[0]);
            case "2":
                return new Paragraph(new Text("2")).add(nd).add(tok[1]).add(tok[0]);
            case "3":
                return new Paragraph(new Text("3")).add(rd).add(tok[1]).add(tok[0]);
            default:
                return new Paragraph(tok[2]).add(th).add(tok[1]).add(tok[0]);
        }
    }

    /**
     * Process ordinal paragraph.
     *
     * @param string the string
     * @param font   the font
     * @return the paragraph
     */
    public static Paragraph processOrdinal(String string, PdfFont font){
        Text st = new Text("st").setFont(font).setTextRise(7).setFontSize(6);
        Text nd = new Text("nd").setFont(font).setTextRise(7).setFontSize(6);
        Text rd = new Text("rd").setFont(font).setTextRise(7).setFontSize(6);
        Text th = new Text("th").setFont(font).setTextRise(7).setFontSize(6);
        switch (string) {
            case "1":
                return new Paragraph(new Text("1")).add(st);
            case "2":
                return new Paragraph(new Text("2")).add(nd);
            case "3":
                return new Paragraph(new Text("3")).add(rd);
            default:
                return new Paragraph(string).add(th);
        }
    }

}
