package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;
import service.UsersDAO;
import serviceimple.UserDAOImpl;

public class UsersAction extends SuperAction  implements ModelDriven<Users> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Users user = new Users();
	
	//�û���¼����
	public String login(){
		UsersDAO udao = new UserDAOImpl();
		if(udao.usersLogin(user)){
			//��session�б����¼�ɹ����û���
			session.setAttribute("loginUserName", user.getUsername());
			
			return "login_success";
		}else{
			return "login_failure";
		}
	}
	
	//�û�ע������
	@SkipValidation
	public String logout(){
		if(session.getAttribute("loginUserName")!=null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	
	
	
	@Override
	public void validate() {
		
		//�û�������Ϊ��
		if("".equals(user.getUsername().trim())){
			
			this.addFieldError("usernameError", "�û�������Ϊ�գ�");
		}
		if(user.getPassword().length()<6){
			this.addFieldError("passwordError", "���볤�Ȳ�����6λ");
		}
		
	}
	@Override
	public Users getModel() {
		
		return this.user;
	}

}