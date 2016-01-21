package co.icoms.triptour.utils;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenny on 21/1/16.
 */
public class Query {

    public interface queryStringListener{
        void onSucces(queryString query, ArrayList<String> data);
    }

    private queryStringListener listener;

    public class queryString{
        private void query(String table, final String column) {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(table);

            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> queryData, ParseException e) {
                    ArrayList<String> data = new ArrayList<String>();
                    if (e == null) {
                        for (int k = 0; k < queryData.size(); k++) {
                            data.add(queryData.get(k).getString(column));
                        }
                    }
                }
            });
        }
    }
}
