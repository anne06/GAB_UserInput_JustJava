/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 2;

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
        String price = createOrderSummary(name, hasWhippedCream, hasChocolate, totalPrice);

        displayMessage(price);

        Toast monToast = Toast.makeText(this, "Bien joue ++", Toast.LENGTH_SHORT);
        monToast.show();

    }

    /**
     * This method creates an order summary
     *
     * @param name
     * @param hasCream
     * @param hasChocolate
     * @param price
     */
    private String createOrderSummary(String name, boolean hasCream, boolean hasChocolate, int price) {
        String summary = "Name: " + name +
                "\nAdd whipped hasCream? " + hasCream +
                "\nAdd chocolate? " + hasChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price +
                "\nThank you!";
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
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void increment(View view) {
        displayQuantity(++quantity);
    }

    public void decrement(View view) {
        displayQuantity(--quantity);
    }

}