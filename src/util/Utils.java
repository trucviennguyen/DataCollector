/*
 * Utils.java
 *
 * CIMeC WikiParser -- a parser for Wikipedia dump
 * Copyright (c) 2012 Truc-Vien T. Nguyen. All Rights Reserved.
 *
 * WikiParser is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WikiParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, bug reports, fixes, contact:
 *    Truc-Vien T. Nguyen
 *    trucvien.nguyen@gmail.com
 *    http://sites.google.com/site/trucviennguyen/
 *
 */

package util;

/**
 * This class prepares documents in MediaWiki markup format so that they can be tagged by a document tagger.
 *
 * @author Truc-Vien T. Nguyen
 */
public final class Utils {

    public static double atod(String s) {
        return Double.valueOf(s).doubleValue();
    }

    public static float atof(String s) {
        return Float.valueOf(s).floatValue();
    }

    public static int atoi(String s) {
        return Integer.parseInt(s);
    }

    public static String[] copyArray(String[] src, int from) {
        String[] des = new String[0];

        if (from < src.length) {
            des = new String[src.length - from];
            for (int i = from; i < src.length; i++)
                des[i - from] = src[i];
        }

        return des;
    }

    public static String[] copyArray(String[] src, int from, int to) {
        String[] des = new String[0];

        if ( (from <= to) && (to < src.length) ) {
            des = new String[to - from + 1];
            for (int i = from; i < to; i++)
                des[i - from] = src[i];
        }

        return des;
    }

}
