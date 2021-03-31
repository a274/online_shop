package com.online_shop.core;


import android.content.Context;
import android.widget.EditText;

import com.online_shop.R;

/**
 * The Interface for processing text fields.
 */

public interface TextProcessing {

    /**
     * method for printing message about error in case of empty text field.
     * @param context current activity
     * @param editText edited text field
     */
    // вывод ошибки о пустой строке
    default void printError(Context context, EditText editText) {
        editText.setHintTextColor(context.getColor(R.color.error));
        editText.setHint(R.string.fill_the_field);
    }

    /**
     * method for checking whether the text fields are empty.
     * @param context current activity
     * @param field edited text fields
     * @return boolean value saying whether the text field is empty.
     */
    //проверка пустой строки
    default boolean isFieldEmpty(Context context, EditText... field) {
        boolean p = false;
        for (EditText editText : field) {
            String value = getValue(editText);
            if (value.equals("")) {
                printError(context, editText);
                p = true;
            }
        }
        return p;
    }

    /**
     * method for shortening the code combining several methods in one.
     * @param field edited text field
     * @return edited string
     */
    default String getValue(EditText field) {
        return field.getText().toString().trim();
    }

}
