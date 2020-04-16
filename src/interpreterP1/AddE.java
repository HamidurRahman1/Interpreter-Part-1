
package interpreterP1;

import java.util.Map;

public class AddE extends FunExp
{
    AddE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "+";
    }

    @Override
    Val Eval(Map<String, Val> map)
    {
        System.out.println(map.size());
        return null;
    }
}