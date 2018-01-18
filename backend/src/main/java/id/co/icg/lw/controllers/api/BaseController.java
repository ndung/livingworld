package id.co.icg.lw.controllers.api;


import id.co.icg.lw.domain.Response;
import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import id.co.icg.lw.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BaseController {

	@Autowired
	private JwtTokenUtil tokenUtil;

    protected final static ResponseEntity<Response> FORBIDDEN = new ResponseEntity<Response>(HttpStatus.FORBIDDEN);

    protected String createToken(User user) {
        return tokenUtil.createToken(user);
    }

    protected boolean authorize(RoleEnum roleRequired, String token) {
        List<RoleEnum> roleTokens = getRoles(token);
        for (RoleEnum rt : roleTokens) {
            if (rt == RoleEnum.ADMIN) return true;
            if (rt == roleRequired) return true;
        }
        return false;
    }

    protected boolean authorize(RoleEnum[] roleRequireds, String token) {
        List<RoleEnum> roleTokens = getRoles(token);
        for (RoleEnum rt : roleTokens) {
            if (rt == RoleEnum.ADMIN) return true;
            for (RoleEnum rr : roleRequireds) {
                if (rr == rt) return true;
            }
        }
        return false;
    }

    protected boolean authenticate(String token) {
        return tokenUtil.authenticate(token);
    }

    protected String getUserId(String token) {
        return tokenUtil.getUserId(token);
    }

    protected String getEmail(String token) {
        return tokenUtil.getEmail(token);
    }

    protected List<RoleEnum> getRoles(String token) {
        String[] roleIds;
        List<RoleEnum> roleEnums = new ArrayList<RoleEnum>();
        try {
            roleIds = tokenUtil.getRoles(token);
            for (String rs : roleIds) {
                int roleId = Integer.parseInt(rs);
                roleEnums.add(RoleEnum.parse(roleId));
            }
        } catch (Exception e) {

		}
        return roleEnums;
    }

    protected boolean isRole(String token, RoleEnum role) {
        List<RoleEnum> roles = getRoles(token);
        for (RoleEnum r : roles) {
            if (r.equals(role)) return true;
        }
        return false;
    }

    protected ResponseEntity<Response> getHttpStatus(Response response) {
        HttpStatus hs = response.getData() == null ? HttpStatus.BAD_REQUEST :
                HttpStatus.OK;
		return new ResponseEntity<Response>(response, hs);
	}

    protected ResponseEntity<Response> getHttpStatus(Response response, HttpHeaders headers) {
        HttpStatus hs = response.getData() == null ? HttpStatus.BAD_REQUEST :
                HttpStatus.OK;
        return new ResponseEntity<Response>(response, headers, hs);
    }
}
