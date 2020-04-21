
package interpreterP1;

import java.util.HashMap;

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
    Val Eval(HashMap<String, Val> state) {
        return null;
    }
}