
package interpreterP1;

import java.util.Map;

public class Pair extends FunExp
{
    Pair(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "pair";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}