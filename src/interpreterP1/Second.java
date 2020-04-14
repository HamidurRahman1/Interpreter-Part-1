
package interpreterP1;

public class Second extends FunExp
{
    Second(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "second";
    }
}