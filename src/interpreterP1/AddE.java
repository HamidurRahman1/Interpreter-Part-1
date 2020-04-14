
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
}