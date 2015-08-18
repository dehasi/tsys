package classes;

import businessLogic.*;
import model.*;
import model.statuses.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;
    private DriverView driverView;


    public DriverView getDriverView() {
        return driverView;
    }

    public void setDriverView(DriverView driverView) {
        this.driverView = driverView;
    }



    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        driverView = LoginDAO.validate(user, pwd);
        if (driverView != null) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }

    public String changeBaggageStatus (int baggageId) {
        List<OrderView> routes =  driverView.getRoute();
        for (int i = 0; i <  routes.size(); i++) {
            if (routes.get(i).getNumber() == baggageId) {
                routes.get(i).setIsDone(DoneStatus.DONE);
                break;
            }
        }
        driverView.setRoute(routes);
        return "admin";
    }

    public String changeDriverStatus (DriverStatus status) {
        if(DriverStatus.DRIVING == status ) {
            status = DriverStatus.WORK;
        } else

        switch (status){
            case DRIVING:{
                status = DriverStatus.WORK;
                break;
            }
            case WORK:{
                status = DriverStatus.DRIVING;
                break;
            }
            case REST: {
                status = DriverStatus.WORK;
                break;
            }
        }
        driverView.getDriver().setStatus(status);

        return "admin";
    }
}