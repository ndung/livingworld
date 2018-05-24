package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.services.master.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Application.API_PATH + "/master")
public class MasterApiController extends BaseController {
    @Autowired
    private MasterService masterService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ResponseEntity<Response> getMaster(@PathVariable) {
//        Response r = userService.register(param);
//        return getHttpStatus(r);
//    }

    /**
     * @api {get} /master?type={type} Get Master by Type
     * @apiGroup Master
     * @apiDescription Mengambil data master berdasarkan kategori
     * @apiParam {String} type Type yang tersedia yaitu `religion`, `martial_status`, `gender`, `nationality`, dan `city`
     * @apiPermission USER
     *  @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiSuccess {int} id
     * @apiSuccess {String} name
     * @apiExample {json} Contoh Response dengan Type = martial_status
     * {
     *     data : [
     *          {
     *              "id" : 1,
     *              "name" : "Single"
     *          },
     *          {
     *              "id" : 2,
     *              "name" : "Married"
     *          },
     *
     *     ],
     *     message : null
     * }
     */
    @RequestMapping("")
    public ResponseEntity<Response> findMaster(@RequestParam("type") String type) {
        if (type.equals("religion")) {
            return getHttpStatus(new Response(masterService.getReligions()));
        } else if (type.equals("martial_status")) {
            return getHttpStatus(new Response(masterService.getMartialStatus()));
        } else if (type.equals("gender")) {
            return getHttpStatus(new Response(masterService.getGenders()));
        } else if (type.equals("nationality")) {
            return getHttpStatus(new Response(masterService.getNationalities()));
        } else if (type.equals("city")) {
            return getHttpStatus(new Response(masterService.getCities()));

        }else {
            return getHttpStatus(new Response(false, "Type is not found"));
        }
    }

    @RequestMapping("/member-types")
    public ResponseEntity<Response> getMemberType() {
        return getHttpStatus(new Response(masterService.getMemberTypes()));
    }
}
