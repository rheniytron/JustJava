package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    int numberOfCoffees = 1;
    int costOfEachCoffee = 5;
    boolean hasChocolateToppings;
    boolean hasWippedCreamToppings;

    public void submitOrder(View view) {

        createOrderSummary(calculatePrice());
//        String sessionGreetings = "free";
//        displayMessage(sessionGreetings);
    }

    private void createOrderSummary(String price) {

        EditText nameEditText   = (EditText) findViewById(R.id.name_edit_text);
        CheckBox wippedCream = (CheckBox) findViewById(R.id.wipped_cream_checkbox);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);

        hasWippedCreamToppings= wippedCream.isChecked();
        hasChocolateToppings = chocolate.isChecked();

        String orderSummary = "";

        if (hasWippedCreamToppings && hasChocolateToppings)
            orderSummary = "Name: "+nameEditText.getText().toString().trim()
                            +"\nAdd Wipped Cream Toppings"
                            +"\nAdd Chocolate Toppings"
                            +"\nQuantity:"+numberOfCoffees
                            +"\n" +price
                            +"\nThank you !";
        else if (hasWippedCreamToppings)
            orderSummary = "Name: "+nameEditText.getText().toString().trim()
                    +"\nAdd Wipped Cream Toppings"
                    +"\nQuantity:"+numberOfCoffees
                    +"\n" +price
                    +"\nThank you !";
        else if (hasChocolateToppings)
            orderSummary = "Name: "+nameEditText.getText().toString().trim()
                    +"\nAdd Chocolate Toppings"
                    +"\nQuantity:"+numberOfCoffees
                    +"\n" +price
                    +"\nThank you !";
        else
            orderSummary = "Name: "+nameEditText.getText().toString().trim()
                    +"\nQuantity:"+numberOfCoffees
                    +"\n" +price
                    +"\nThank you !";

        sendEmail(new String[]{"rheniytron0270@gmail.com"},"Ice Cream Order Summary", orderSummary);
    }

    private String calculatePrice() {
        displayQuantity(numberOfCoffees);

        int cost;
        if (hasChocolateToppings && hasChocolateToppings)
            cost = numberOfCoffees * (costOfEachCoffee + 3);
        else if (hasChocolateToppings)
            cost = numberOfCoffees * (costOfEachCoffee + 2);
        else if (hasWippedCreamToppings)
            cost = numberOfCoffees * (costOfEachCoffee +1);
        else
            cost = numberOfCoffees * costOfEachCoffee;

//        TextView priceTextView  = (TextView) findViewById(R.id.price_tv);
//        priceTextView.setText("Total: "+NumberFormat.getCurrencyInstance().format(cost));

        return "Total: "+NumberFormat.getCurrencyInstance().format(cost);
    }

    public void sendEmail(String[] addresses, String subject, String mgs) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, mgs);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_tv);
        quantityTextView.setText(""+number);
    }

//    private void displayMessage(String mgs){
//        TextView priceTextView = (TextView) findViewById(R.id.price_tv);
//        priceTextView.setText("Total: "+mgs+"\nThank you!");
//    }

    public void increment(View view) {
        if (numberOfCoffees<100)
        displayQuantity(numberOfCoffees+=1);
    }

    public void decrement(View view) {
        if(numberOfCoffees>1)
            displayQuantity(numberOfCoffees-=1);
    }

    //  >>  court_counter

    int court_score_a = 0;
    int court_score_b = 0;

    private void setScore(int score, char team){
        TextView score_TextView = (TextView) findViewById(R.id.team_a_score);
        TextView score_TextView1 = (TextView) findViewById(R.id.team_b_score);
        if ("A".equalsIgnoreCase(String.valueOf(team)))
            score_TextView.setText(String.valueOf(court_score_a+=score));
        if ("B".equalsIgnoreCase(String.valueOf(team)))
            score_TextView1.setText(String.valueOf(court_score_b+=score));
    }
    public void plus_3_points(View view) {
        setScore(3,'A');
    }

    public void plus_2_points(View view) {
    setScore(2,'A');
    }

    public void free_throw(View view) {
    setScore(1,'A');
    }

    //Team B

    public void plus_3_points1(View view) {
        setScore(3,'B');
    }

    public void plus_2_points1(View view) {
        setScore(2,'B');
    }

    public void free_throw1(View view) {
        setScore(1,'B');
    }

    public void resetButton(View view) {
        court_score_a = 0;
        court_score_b = 0;
        setScore(0,'A');
        setScore(0,'B');
    }




    //Cookies testing

    public void eat_cookie(View view) {

        ImageView cookie_ImageView = (ImageView) findViewById(R.id.cookies_iv);
        TextView  cookie_TextView = (TextView) findViewById(R.id.hungry);
        Button    eat_Button        = (Button) findViewById(R.id.eat_bttn);

        if (cookie_TextView.getText().toString().equals("I'm so full")){
            cookie_ImageView.setImageResource(R.drawable.before_cookie);
            cookie_TextView.setText("I'm so Hungry");

            eat_Button.setText("Eat Cookie");

            return;
        }
        cookie_ImageView.setImageResource(R.drawable.after_cookie);
        cookie_TextView.setText("I'm so full");

        eat_Button.setText("More Cookies");

    }

}
