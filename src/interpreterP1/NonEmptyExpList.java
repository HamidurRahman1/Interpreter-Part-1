
package interpreterP1;

public class NonEmptyExpList extends ExpList
{
    public Exp exp;
    public ExpList expList;

    public NonEmptyExpList(Exp e, ExpList el)
    {
        exp = e;
        expList = el;
    }
}