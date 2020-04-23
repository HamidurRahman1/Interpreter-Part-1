
package interpreterPart1;

public class MultipleFunDef extends FunDefList
{
    public FunDef funDef;
    public FunDefList funDefList;

    public MultipleFunDef(FunDef fdef, FunDefList fdeflist)
    {
        funDef = fdef;
        funDefList = fdeflist;
    }
}