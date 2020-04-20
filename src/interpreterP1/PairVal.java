
package interpreterP1;

import java.util.Objects;

class PairVal extends Val
{
    Val first;
    Val second;

    public PairVal(Val first, Val second) {
        this.first = first;
        this.second = second;
    }

    public String toString()
    {
        return "pair("+first+", "+second+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairVal)) return false;
        PairVal pairVal = (PairVal) o;
        return Objects.equals(first, pairVal.first) &&
                Objects.equals(second, pairVal.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public Val cloneVal() {
        return null;
    }
}