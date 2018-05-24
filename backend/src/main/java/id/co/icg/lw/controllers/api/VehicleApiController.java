package id.co.icg.lw.controllers.api;

import id.co.icg.lw.Application;
import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.Vehicle;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.services.member.CreateMemberRequest;
import id.co.icg.lw.services.member.MemberResponse;
import id.co.icg.lw.services.member.MemberService;
import id.co.icg.lw.services.user.EditUserRequest;
import id.co.icg.lw.services.vehicle.CreateVehicleRequest;
import id.co.icg.lw.services.vehicle.UpdateVehicleRequest;
import id.co.icg.lw.services.vehicle.VehicleResponse;
import id.co.icg.lw.services.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Application.API_PATH + "/vehicle")
public class VehicleApiController extends BaseController {

    @Autowired
    private VehicleService vehicleService;


    /**
     * @api {post} /vehicle/create Create Vehicle
     * @apiName Create Vehicle
     * @apiGroup Vehicle
     * @apiPermission USER
     * @apiDescription Create vehicle
     *
     * @apiParam {String} vehicleType
     * @apiParam {String} vehicleColor
     * @apiParam {String} vehicleNumber
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {json} Request
     * {
     *
     *   "vehicleType" : "BMW",
     *   "vehicleColor" : "Red",
     *   "vehicleNumber" : "B 1234 CD"
     *
     * }
     * @apiExample {json} Response Berhasil
     * {
     *  "vehicleId" : "3d3bb860-870d-4d4f-bd7c-e6ce17c78f39",
     *   "vehicleType" : "BMW",
     *   "vehicleColor" : "Red",
     *   "vehicleNumber" : "B 1234 CD"
     *
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Failed"
     * }
     *

     *
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response> memberRegistration(@RequestHeader(Application.AUTH) String token,
                                                       @RequestBody CreateVehicleRequest request) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId   = getUserId(token);
            request.setUserId(userId);

            return getHttpStatus(new Response(new VehicleResponse(vehicleService.create(request))));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /vehicle/edit Edit Vehicle
     * @apiName Edit Vehicle
     * @apiGroup Vehicle
     * @apiPermission USER
     * @apiDescription Create vehicle
     *
     * @apiParam {String} vehicleId
     * @apiParam {String} vehicleType
     * @apiParam {String} vehicleColor
     *
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {json} Request
     * {
     *
     *   "vehicleId" : "3d3bb860-870d-4d4f-bd7c-e6ce17c78f39"
     *   "vehicleType" : "BMW",
     *   "vehicleColor" : "Blue"
     *
     * }
     * @apiExample {json} Response Berhasil
     * {
     *  "vehicleId" : "3d3bb860-870d-4d4f-bd7c-e6ce17c78f39",
     *   "vehicleType" : "BMW",
     *   "vehicleColor" : "Blue",
     *   "vehicleNumber" : "B 1234 CD"
     *
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Failed"
     * }
     *

     *
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<Response> memberRegistration(@RequestHeader(Application.AUTH) String token,
                                                       @RequestBody UpdateVehicleRequest request) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId   = getUserId(token);
            request.setUserId(userId);

            return getHttpStatus(new Response(new VehicleResponse(vehicleService.update(request))));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {delete} /vehicle/{vehicleId}
     * @apiName Delete Vehicle
     * @apiGroup Vehicle
     * @apiPermission USER
     * @apiDescription Delete vehicle
     *
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     * @apiExample {json} Response Berhasil
     * {
     *      data: true,
     *      message: null
     *
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: false,
     *      message: null
     * }
     *

     *
     */
    @RequestMapping(value = "/delete/{vehicleId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> memberRegistration(@RequestHeader(Application.AUTH) String token,
                                                       @PathVariable String vehicleId) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            return getHttpStatus(new Response(vehicleService.delete(vehicleId)));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

    /**
     * @api {post} /vehicle/edit Edit Vehicle
     * @apiName Edit Vehicle
     * @apiGroup Vehicle
     * @apiPermission USER
     * @apiDescription Create vehicle
     *
     * @apiParam {String} vehicleId
     * @apiParam {String} vehicleType
     * @apiParam {String} vehicleColor
     *
     *
     * @apiHeader {String} Authorization Token hasil generate dari Sign In ditambahkan di header
     * @apiHeaderExample {json} Contoh Token Header
     * {
     *     "Authorization" : "B3CDB813C735BF6D93ED713E1E94D351EF43213A382EB43C64A677F7D43BB0FC"
     * }
     *
     * @apiExample {json} Request
     * {
     *
     *   "vehicleId" : "3d3bb860-870d-4d4f-bd7c-e6ce17c78f39"
     *   "vehicleType" : "BMW",
     *   "vehicleColor" : "Blue"
     *
     * }
     * @apiExample {json} Response Berhasil
     * {
     *  "vehicleId" : "3d3bb860-870d-4d4f-bd7c-e6ce17c78f39",
     *   "vehicleType" : "BMW",
     *   "vehicleColor" : "Blue",
     *   "vehicleNumber" : "B 1234 CD"
     *
     * }
     * @apiExample {json} Response Gagal
     * {
     *      data: null,
     *      message: "Failed"
     * }
     *

     *
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Response> memberRegistration(@RequestHeader(Application.AUTH) String token) {
        if (!authorize(RoleEnum.USER, token)) {
            return FORBIDDEN;
        }

        try {
            String userId   = getUserId(token);
            return getHttpStatus(new Response(vehicleService.findByUser(userId)));
        } catch (Exception e) {
            return getHttpStatus(new Response(e.getMessage()));
        }
    }

}
