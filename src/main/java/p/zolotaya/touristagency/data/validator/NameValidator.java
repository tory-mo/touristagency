package p.zolotaya.touristagency.data.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements Validator {
    private Pattern p = Pattern.compile("^[A-Za-z]+$");
    public NameValidator(){
        super();
    }
    @Override
    public boolean validate(String name){
        Matcher m = p.matcher(name);
        return m.find();
    }

}
