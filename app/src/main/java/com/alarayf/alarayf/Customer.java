package com.alarayf.alarayf;

import android.app.Application;

/**
 * Created by Mohammad on 1/22/18.
 */


public class Customer extends Application{

    int Cust_number = 2;   // 1- for alshowaib, 2 - for alarayf. change this base on customer

    //int ID;

    static String Name;
    static String Tree_Name;
    static String Site_URL;
    static int Main_Background ;
    static int Page_Background;

    public static void setNotification_icon(int notification_icon) {
        Notification_icon = notification_icon;
    }

    public static int getNotification_icon() {
        return Notification_icon;

    }

    static  int Notification_icon;
    public static int getTree_image() {
        return Tree_image;
    }

    static int Tree_image;

    public static String getFont_Name() {
        return Font_Name;
    }

    public static void setTree_image(int tree_image) {
        Tree_image = tree_image;
    }

    static String Server_image_Path;
    static String Font_Name;
    static String Twitter_URL;
    static String Instagram_URL;


    static String Tree_image_URL;
    static int About_Us_Background;
    static int Event_image_Name;

    public static void setTree_Hierarchy_URL(String tree_Hierarchy_URL) {
        Tree_Hierarchy_URL = tree_Hierarchy_URL;
    }

    public static String getTree_Hierarchy_URL() {

        return Tree_Hierarchy_URL;
    }

    static String Tree_Hierarchy_URL;

    static  String Send_Email_Comments_URL;
    static  String Admin_Email;

    public static String getAdmin_Email_Notification() {
        return Admin_Email_Notification;
    }

    static  String Tree_Email;
    static  String Send_Email_URL;
    static  String Send_Email_Event_URL;
    static  String Admin_Email_Notification;
    static  String Vote_URL;
    static  String Vote_Publish;

    public static String getServer_image_Path() {
        return Server_image_Path;
    }



    public static String getSite_URL() {
        return Site_URL;
    }

    public static int getEvent_image_Name() {
        return Event_image_Name;
    }

    public int getCust_number() {
        return Cust_number;
    }

    public static String getName() {
        return Name;
    }

    public static int getMain_Background() {
        return Main_Background;
    }

    public static int getPage_Background() {
        return Page_Background;
    }

    public void setCust_number(int cust_number) {
        Cust_number = cust_number;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static void setTree_Name(String tree_Name) {
        Tree_Name = tree_Name;
    }

    public static void setSite_URL(String site_URL) {
        Site_URL = site_URL;
    }

    public static void setMain_Background(int main_Background) {
        Main_Background = main_Background;
    }

    public static void setPage_Background(int page_Background) {
        Page_Background = page_Background;
    }

    public static void setServer_image_Path(String server_image_Path) {
        Server_image_Path = server_image_Path;
    }

    public static void setFont_Name(String font_Name) {
        Font_Name = font_Name;
    }

    public static void setTwitter_URL(String twitter_URL) {
        Twitter_URL = twitter_URL;
    }

    public static void setInstagram_URL(String instagram_URL) {
        Instagram_URL = instagram_URL;
    }

    public static void setTree_image_URL(String tree_image_URL) {
        Tree_image_URL = tree_image_URL;
    }

    public static void setAbout_Us_Background(int about_Us_Background) {
        About_Us_Background = about_Us_Background;
    }

    public static void setEvent_image_Name(int event_image_Name) {
        Event_image_Name = event_image_Name;
    }

    public static void setSend_Email_Comments_URL(String send_Email_Comments_URL) {
        Send_Email_Comments_URL = send_Email_Comments_URL;
    }

    public static void setAdmin_Email(String admin_Email) {
        Admin_Email = admin_Email;
    }

    public static void setTree_Email(String tree_Email) {
        Tree_Email = tree_Email;
    }

    public static void setSend_Email_URL(String send_Email_URL) {
        Send_Email_URL = send_Email_URL;
    }

    public static void setSend_Email_Event_URL(String send_Email_Event_URL) {
        Send_Email_Event_URL = send_Email_Event_URL;
    }

    public static void setAdmin_Email_Notification(String admin_Email_Notification) {
        Admin_Email_Notification = admin_Email_Notification;
    }

    public static void setVote_URL(String vote_URL) {
        Vote_URL = vote_URL;
    }

    public static void setVote_Publish(String vote_Publish) {
        Vote_Publish = vote_Publish;
    }

    public static String getTwitter_URL() {
        return Twitter_URL;
    }

    public static String getInstagram_URL() {
        return Instagram_URL;
    }

    public static String getTree_image_URL() {
        return Tree_image_URL;
    }

    public static int getAbout_Us_Background() {
        return About_Us_Background;
    }

    public static String getSend_Email_Comments_URL() {
        return Send_Email_Comments_URL;
    }

    public static String getAdmin_Email() {
        return Admin_Email;
    }

    public static String getTree_Email() {
        return Tree_Email;
    }

    public static String getSend_Email_URL() {
        return Send_Email_URL;
    }

    public static String getSend_Email_Event_URL() {
        return Send_Email_Event_URL;
    }

    public static String getVote_URL() {
        return Vote_URL;
    }

    public static String getVote_Publish() {
        return Vote_Publish;
    }

    public Customer() {


        switch (Cust_number) {
            case 1:
                Name = "تطبيق الشوايبة";
                Tree_Name = "شجرة الشوايبة";
                Main_Background = com.alarayf.alarayf.R.drawable.background;
                Page_Background = com.alarayf.alarayf.R.drawable.backgroundclassic;
                Notification_icon = R.drawable.treeicon;
                Event_image_Name = com.alarayf.alarayf.R.drawable.treeicon;
                Site_URL = "http://www.alarayf.com/m50/";
                Server_image_Path = "http://www.alarayf.com/media/k2/items/cache/";
                Instagram_URL = "https://www.instagram.com/alarayf/?hl=ar";
                Tree_image_URL = "http://www.alarayf.com/tree/alshowaib_tree.png";
                Tree_Hierarchy_URL = "http://alaraayf.com/v1/m50/Tree/treeview.php";
                About_Us_Background = com.alarayf.alarayf.R.drawable.about;
                Font_Name = "Laha.ttf";
                Send_Email_Comments_URL = "http://www.alaraayf.com/v1/m40/sendEmailCommentsNotification.php";
                Admin_Email = "mhmh556@alarayf.com";
                Tree_Email  = "mhmh556@alarayf.com";
                Send_Email_URL = "http://www.alarayf.com/m40/sendEmail.php";

                Send_Email_Event_URL = "http://www.alarayf.com/m40/sendEmailEvent.php";
                Admin_Email_Notification = "mhmh556@gmail.com";

                Vote_URL = "https://survey.zohopublic.com/zs/vYB0oW";

                Vote_Publish = "0";
                break;

            case 2:


                Name = "تطبيق العرايف";
                Tree_Name = "شجرة العرايف";
                Main_Background = com.alarayf.alarayf.R.drawable.background4;
                Page_Background = com.alarayf.alarayf.R.drawable.background5;
                Event_image_Name = com.alarayf.alarayf.R.drawable.alarayf0icon;
                Notification_icon = R.drawable.alarayf0icon;
                Site_URL = "http://www.alaraayf.com/v1/m50/";
                Server_image_Path = "http://www.alaraayf.com/v1/media/k2/items/cache/";
                Instagram_URL = "https://www.instagram.com/alaraayf/";
                Tree_image_URL = "http://www.alaraayf.com/v1/tree/tree.png";
                Tree_Hierarchy_URL = "http://alaraayf.com/v1/m50/Tree/treeview.php";
                About_Us_Background = com.alarayf.alarayf.R.drawable.background4;
                Font_Name = "Times New Roman Bold.ttf";

                Send_Email_Comments_URL = "http://www.alarayf.com/m40/sendEmailCommentsNotification.php";


                Admin_Email = "admin@alaraayf.com";
                Tree_Email  = "admin@alaraayf.com";
                Send_Email_URL = "http://www.alaraayf.com/v1/m40/sendEmail.php";

                Send_Email_Event_URL = "http://www.alaraayf.com/v1/m40/sendEmailEvent.php";
                Admin_Email_Notification = "alaraayf@gmail.com";
                //Admin_Email_Notification = "mhmh556@gmail.com";


                Vote_URL = "https://survey.zohopublic.com/zs/vYB0oW";
                Vote_Publish = "0";
                break;

            default:

                Name = "Alshowaib";
                Tree_Name = "شجرة الشوايبة";
                Main_Background = com.alarayf.alarayf.R.drawable.background;
                Page_Background = com.alarayf.alarayf.R.drawable.backgroundclassic;
                Notification_icon = R.drawable.treeicon;
                Event_image_Name = com.alarayf.alarayf.R.drawable.treeicon;
                Site_URL = "http://www.alaraayf.com/v1/m40/";
                Server_image_Path = "http://www.alaraayf.com/v1/media/k2/items/cache/";
                Instagram_URL = "https://www.instagram.com/alarayf/?hl=ar";
                Tree_image_URL = "http://www.alaraayf.com/v1/tree/tree.png";
                Tree_Hierarchy_URL = "http://alaraayf.com/v1/m50/Tree/treeview.php";
                About_Us_Background = com.alarayf.alarayf.R.drawable.about;
                Font_Name = "Times New Roman Bold.ttf";

                Send_Email_Comments_URL = "http://www.alaraayf.com/v1/m40/sendEmailCommentsNotification.php";

                Admin_Email = "mhmh556@alarayf.com";
                Tree_Email  = "mhmh556@alarayf.com";
                Send_Email_URL = "http://www.alarayf.com/m40/sendEmail.php";

                Send_Email_Event_URL = "http://www.alarayf.com/m40/sendEmailEvent.php";
                Admin_Email_Notification = "mhmh556@gmail.com";

                Vote_URL = "https://survey.zohopublic.com/zs/vYB0oW";

                Vote_Publish = "0";

                break;
        }
    }




    public static String getTree_Name() {
        return Tree_Name;
    }





}


