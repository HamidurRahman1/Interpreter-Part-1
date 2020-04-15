
package interpreterP1;

import java.util.Map;

public class Second extends FunExp
{
    Second(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "second";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}