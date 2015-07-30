package Controlers;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

/**
 * Some util functions for controoolers worl
 */
public class UtilsController {
    private static Logger logger = Logger.getLogger(UtilsController.class);
    public static ModelAndView handleError(Exception e)  {
        logger.error(e);
        e.printStackTrace();
        return new ModelAndView("/error.jsp").addObject("error", e);
    }
}
