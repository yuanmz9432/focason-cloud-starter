package com.focason.core.resource;



import java.util.Map;
import lombok.*;

/**
 * EmailResource
 *
 * @author Focason Lab Team
 * @version 1.0.0
 * @since 1.0.0
 */
@Builder public record EmailResource(Integer emailType,String from,String to,String cc,String subject,String body,Map<String,Object>payload){}
