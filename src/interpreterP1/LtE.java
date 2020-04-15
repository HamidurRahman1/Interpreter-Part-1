
package interpreterP1;

import java.util.Map;

public class LtE extends FunExp
{
    LtE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "<";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}