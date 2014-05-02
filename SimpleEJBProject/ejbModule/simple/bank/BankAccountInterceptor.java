package simple.bank;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BankAccountInterceptor {

	@AroundInvoke
	public Object methodInvoked(InvocationContext ctx) throws Exception {
	  System.out.println("A method was invoked!");
	  String methodName = ctx.getMethod().getName();
	  System.out.println("The method was: " + methodName);
	  Object params[] = ctx.getParameters();
	  System.out.println("The parameters are: ");
	  for(int x = 0; x != params.length; x++) {
	     System.out.println(params[x]);
	  }
	  return ctx.proceed();
	}
}
