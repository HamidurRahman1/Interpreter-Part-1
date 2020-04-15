
package interpreterP1;

import java.util.Map;

public class SubE extends FunExp
{
    SubE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "-";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}