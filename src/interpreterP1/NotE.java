
package interpreterP1;

import java.util.HashMap;

class NotE extends FunExp
{
    NotE(ExpList e)
    {
        expList = e;
    }

    String getFunOp()
    {
        return "not";
    }

    @Override
    Val Eval(HashMap<String, Val> state)
    {
        if(expList.getClass() == EmptyExpList.class)
        {
            System.out.println("Error: not operator missing arguments");
            return null;
        }
        NonEmptyExpList ne = (NonEmptyExpList)expList;

        while (ne.exp != null)
        {
            state.put(getFunOp(), ne.exp.Eval(state));
            if(ne.expList.getClass() == NonEmptyExpList.class) ne = (NonEmptyExpList)ne.expList;
            else break;
        }

        state.replace(getFunOp(), new BoolVal(!Boolean.valueOf(state.get(getFunOp()).toString())));

        return state.get(getFunOp());
    }
}