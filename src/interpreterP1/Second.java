
package interpreterP1;

import java.util.HashMap;

class Second extends FunExp
{
    Second(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "second";
    }

    @Override
    Val Eval(HashMap<String, Val> state)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: second operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList) expList;

        while (ne.expList != null)
        {
            try {
                PairVal v = (PairVal)ne.exp.Eval(state);
                state.put(getFunOp(), v.second);
            }
            catch (Exception e)
            {
                System.out.println("Error: second operator cannot be applied to "+ne.exp.Eval(state));
                return null;
            }

            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        return state.get(getFunOp());
    }
}