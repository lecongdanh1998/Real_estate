package vn.edu.poly.realestate.Util;

import java.io.Serializable;

public class ValidateForm implements Serializable {

    public boolean validateTextEmpty(String email){
        if (email.matches("")){
            return true;
        } else {
            return false;
        }
    }
}
