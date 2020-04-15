
package interpreterP1;

import java.util.Map;

public class GtE extends FunExp
{
    GtE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return ">";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}