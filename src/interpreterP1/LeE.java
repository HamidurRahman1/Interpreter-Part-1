
package interpreterP1;

public class LeE extends FunExp
{
    LeE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "<=";
    }
}