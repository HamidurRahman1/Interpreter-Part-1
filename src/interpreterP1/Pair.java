
package interpreterP1;

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
}