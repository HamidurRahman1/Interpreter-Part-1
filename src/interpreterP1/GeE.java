
package interpreterP1;

public class GeE extends FunExp
{
    GeE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return ">=";
    }
}