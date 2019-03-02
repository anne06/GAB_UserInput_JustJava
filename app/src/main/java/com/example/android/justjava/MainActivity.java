/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 1;
    private static final String Log_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Look for the name
        String name = ((EditText) findViewById(R.id.name)).getText().toString();

        // Verify if Whipped cream is selected
        CheckBox cream_cb = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = cream_cb.isChecked();

        // same for the chocolate checkbox
        CheckBox chocolate_cb = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate_cb.isChecked();

        // compute the total price of the order
        int totalPrice = calculatePrice(hasWhippedCream, hasChocolate);

        // create the string which summarize the order
        String summary = createOrderSummary(name, hasWhippedCream, hasChocolate, totalPrice);

        // displayMessage(price);
        sendASummaryEmail(summary);

        Toast.makeText(this, R.string.good_job, Toast.LENGTH_SHORT).show();

    }

    /**
     * Create an intent and send an email to recap the order
     *
     * @param summary
     */
    private void sendASummaryEmail(String summary) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method creates an order summary
     *
     * @param lastName
     * @param hasCream
     * @param hasChocolate
     * @param price
     */
    private String createOrderSummary(String lastName, boolean hasCream, boolean hasChocolate, int price) {
        String summary = getString(R.string.complete_name, lastName) +
                "\n" + getString(R.string.order_summary_whipped_cream, hasCream) +
                "\n" + getString(R.string.order_summary_chocolate, hasChocolate) +
                "\n" + getString(R.string.order_summary_quantity, quantity) +
                "\n" + getString(R.string.order_summary_price,NumberFormat.getCurrencyInstance().format(price))  +
                "\n" + getString(R.string.thanks);

        Log.d(Log_TAG, summary);
        return (summary);

    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream
     * @param hasChocolate
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = 5;
        if (hasWhippedCream)
            price++;
        if (hasChocolate)
            price += 2;

        return price * quantity;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    public void increment(View view) {

        if (quantity == 100){
            Toast.makeText(this, "You cannot order more than 100 coffees", Toast.LENGTH_SHORT).show();
        } else {
            displayQuantity(++quantity);
        }
    }

    public void decrement(View view) {
        if (quantity == 1){
            Toast.makeText(this, "You cannot order less than 1 coffee", Toast.LENGTH_SHORT).show();
        } else {
            displayQuantity(--quantity);
        }
    }

}