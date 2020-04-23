
package interpreterPart1;

public class PairVal extends Val
{
    public Val first;
    public Val second;

    public PairVal(Val first, Val second) {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "pair("+first+", "+second+")";
    }

    @Override
    public Val cloneVal() {
        return new PairVal(first, second);
    }
}