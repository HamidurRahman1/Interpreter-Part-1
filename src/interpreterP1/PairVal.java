
package interpreterP1;

public class PairVal extends Val
{
    Val first;
    Val second;

    public String toString()
    {
        return "pair("+first+", "+second+")";
    }

    @Override
    public Val cloneVal() {
        return null;
    }
}