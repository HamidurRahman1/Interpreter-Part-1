
package interpreterP1;

import java.util.Map;

class FunCall extends FunExp
{
    Id func;  // identifier "func" may be a defined function name or a variable

    FunCall(Id i, ExpList e)
    {
        func = i;
        expList = e;
    }

    String getFunOp()
    {
        return func.id;
    }

    @Override
    Val Eval(Map<String, Val> map) {
        return null;
    }
}