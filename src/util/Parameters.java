/*
 * Parameters.java
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

import java.io.Serializable;
import java.util.StringTokenizer;

/**
 *
 * @author Truc-Vien T. Nguyen
 */
public class Parameters implements Cloneable, Serializable
{
    public static int mode = 0;

    /**  */
    public static int n = -1;

    public static Parameters reckParameters = null;

    public static String inputFilename = null,
                        outputFilename = null;

    public Parameters () {
        reckParameters = this;
    }

    public static void processParameters (String[] args) {
        int k = 4;
        if (args[0].equalsIgnoreCase("-s")) 
            mode = Integer.parseInt(args[1]);
        else
            k = 2;
        inputFilename = args[k - 2];
        outputFilename = args[k - 1];
        String[] argv = Utils.copyArray(args, k);
        parse(argv);
    }

    public static void processParameters(String sParams) {

        // parse options
        StringTokenizer st = new StringTokenizer(sParams);
        String[] args = new String[st.countTokens()];
        for (int i = 0;i < args.length;i++)
            args[i] = st.nextToken();
        int k = 4;
        if (args[0].equalsIgnoreCase("-s")) 
            mode = Integer.parseInt(args[1]);
        else
            k = 2;
        inputFilename = args[k - 2];
        outputFilename = args[k - 1];
        String[] argv = Utils.copyArray(args, k);
        parse(argv);
    }

    public static Parameters getParameters() {
        return reckParameters;
    }

    public static void parse(String[] argv) {

        // parse options
        for (int i=0;i<argv.length;i++)
        {
            if(argv[i].charAt(0) != '-') break;
            if(++i>=argv.length)
            {
                    System.err.print("unknown option\n");
                    break;
            }
            switch(argv[i-1].charAt(1))
            {
                case 'n':
                    n = Utils.atoi(argv[i]);
                    break;

                default:
                    System.err.print("unknown option\n");
            }
        }  
    }
    
    @Override
    public String toString() {
        String st = "";
        st = "-n " + n;
        return st;
    }

}