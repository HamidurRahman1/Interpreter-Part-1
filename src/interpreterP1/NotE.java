
package interpreterP1;

import java.util.Map;

public class NotE extends FunExp
{
    NotE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "not";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}