package com.example.uza;

public class clsGlobal {
    public static String protocol               = "http://eaglefrontiersfashion.co.ke";
    public static String hostname               = "/uza";

//    public static String protocol               = "http://";
//    public static String hostname               = "192.168.43.187/uza/php";

    //10.0.2.2


    public static String REGISTER_USER              = protocol + hostname + "/register_user.php";
    public static String CHECK_USER                 = protocol + hostname + "/check_user.php";
    public static String CHECK_CART                 = protocol + hostname + "/check_cart.php";
    public static String CHECK_QUANTITY             = protocol + hostname + "/check_quantity.php";
    public static String ADD_TO_CART                = protocol + hostname + "/add_to_cart.php";
    public static String LOGIN_USER                 = protocol + hostname + "/login_user.php";
    public static String ALL_CATEGORIES             = protocol + hostname + "/all_categories.php";
    public static String ALL_PRODUCTS               = protocol + hostname + "/all_products.php";
    public static String ALL_CART_PRODUCTS          = protocol + hostname + "/all_cart_products.php";
    public static String SEARCH_PRODUCTS            = protocol + hostname + "/product_search.php";
    public static String REMOVE_PRODUCT             = protocol + hostname + "/remove_from_cart.php";
    public static String UPDATE_QUANTITY            = protocol + hostname + "/update_cart_quantity.php";
    public static String CHECK_CART_QUANTITY        = protocol + hostname + "/check_stock_availability.php";
    public static String UPDATE_PAYMENT             = protocol + hostname + "/payment.php";
    public static String UPDATE_PURCHASES           = protocol + hostname + "/purchases.php";
    public static String UPDATE_PRODUCT_QUANTITY    = protocol + hostname + "/update_product_quantity.php";
    public static String UPDATE_PURCHASE_CART       = protocol + hostname + "/update_cart_purchased.php";
    public static String MY_ORDERS                  = protocol + hostname + "/my_orders.php";


    public static String shared_user_id;
    public static String shared_fname;
    public static String shared_lname;
    public static String shared_email;
    public static String shared_phone;
    public static String shared_password;
    public static String shared_location;
    public static String shared_date_reg;
    public static String shared_prof_pic;
}
