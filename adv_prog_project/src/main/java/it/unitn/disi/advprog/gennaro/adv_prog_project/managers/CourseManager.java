package it.unitn.disi.advprog.gennaro.adv_prog_project.managers;

import it.unitn.disi.advprog.gennaro.adv_prog_project.beans.CourseBean;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dto.CourseDto;
import it.unitn.disi.advprog.gennaro.adv_prog_project.dtoAssembler.DtoAssembler;
import jakarta.ejb.*;
import org.jboss.logging.Logger;

@Stateless
@Local
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CourseManager {
    @EJB
    private CourseBean courseBean;

    private static final org.jboss.logging.Logger logger = Logger.getLogger(TeacherManager.class);

    public CourseDto getCourseById(int id) {
        logger.info("Retrieving course [ " + id + " ] info");
        return DtoAssembler.getCourseDto(this.courseBean.getCourseById(id));
    }
}
