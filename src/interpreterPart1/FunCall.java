
package interpreterPart1;

import java.util.Map;

public class FunCall extends FunExp
{
    public Id func;

    public FunCall(Id i, ExpList e)
    {
        func = i;
        expList = e;
    }

    public String getFunOp()
    {
        return func.id;
    }

    @Override
    public Val Eval(Map<String, Val> valMap) {
        return null;
    }
}