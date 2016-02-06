package co.icoms.triptour.utils;

import java.text.SimpleDateFormat;

public class Final {

    static public class Places{
        public static final String ROATAN="roatan";
        public static final String AMAPALA="amapala";
        public static final String CEIBA="ceiba";
    }

    static public class DataHotel{
        public static final String DATA="hotel_data";
        public static final String PLACE="hotel_place";
        public static final String ID="hotel_id";
        public static final String NAME="hotel_name";
        public static final String IMAGE="hotel_image";
        public static final String PRICE="hotel_price";
        public static final String STARS="hotel_stars";
    }

    static public class DataLogin{
        public static final String STATE="login_state";
        public static final String RELOGIN="login_relogin";
    }

    static public class LifeCycle{
        public static final String STATE="life_cycle_state";
    }

    static public class Permissions{
        public static final int FINE_LOCATION=1;
    }

    static public class TableDetailHotelReview{
        public static final String NUMBER="number";
        public static final String DATE="createdAt";
        public static final String REVIEW="review";
        public static final String EMAIL="email";
        public static final String STARS="stars";
    }

    static public class DateFormat{
        public static final SimpleDateFormat DateFormat=new SimpleDateFormat("dd/MMM/yyy");
        public static final SimpleDateFormat ParseFormat=new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
    }

    static public class ViewTypeHotelReview{
        public static final int INPUT_REVIEW=0;
        public static final int REVIEW=1;
    }
}
