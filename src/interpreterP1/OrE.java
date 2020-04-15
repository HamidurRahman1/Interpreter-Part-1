
package interpreterP1;

import java.util.Map;

public class OrE extends FunExp
{
    OrE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "or";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}