/*
 * Constants.java
 *
 * Translator -- a translator from facebook info to ontology
 * Copyright (c) 2012 Truc-Vien T. Nguyen. All Rights Reserved.
 *
 * Translator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Translator is distributed in the hope that it will be useful,
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

import java.util.logging.Logger;

/**
 * This class provides the top-level API and command-line interface 
 * <p>
 * See the package documentation for more details and examples of use.
 * See the main method documentation for details of invoking the extractor.
 * <p>
 * Note that the composite kernel integrated with dependency parse requires
 * a fair amount of memory and time.  Try -Xmx1024m.
 *
 * @author Truc-Vien T. Nguyen
 */
public class Constants {

/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
/**
 * General constants
 * 
 */
/*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/
    /** The log */
    public static final Logger LOG = Logger.getLogger("util.Constants");

    /** Local fashion for file separators. */
    public static String fileSeparator = System.getProperty("file.separator");

    /** encoding */
    public static String encoding = "UTF-8";

    /* filename for relation type + entity types */
    public static String inputFilename = "input";

    /* filename for relation type + entity types */
    public static String outputFilename = "output";

    /* filename for relation type + entity types */
    public static String IDsFilename = "UserIDs.txt";
    // 
    public static String INFO_TAIL = ".info";
    public static String POST_TAIL = ".post";
    
    /*  */
    public static String accessToken = "CAACEdEose0cBAMcDXJtyNFwnVO9EqyMkesJpdBZBNrW0XnxVMhV1PfwbGCMlOPB47gRrO4jGpBJcGL3natNGRciJPlR01ZAc12ZB65cZBaBDIUHqvV2AsfjwqtZCL6dVbwhBiKubQvApvjFuaf2ZAhVCh3IQ4O8jQh72uC1V0eLsECojHypbkWZAzMwdpABla5QZBMi6OCTTugZDZD";
    
    /*  */
    public static String myId = "100004224780683";
    public static String bobsId = "614489748";
    

}
