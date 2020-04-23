
package interpreterP1;

public class NonEmptyParameterList extends ParameterList
{
    public String id;
    public ParameterList parameterList;

    public NonEmptyParameterList(String s, ParameterList pl)
    {
        id = s;
        parameterList = pl;
    }
}