
package interpreterP1;

import java.util.Map;

public class First extends FunExp
{
    First(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "first";
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}