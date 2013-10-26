package ClientHTTP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.sun.rowset.WebRowSetImpl;

//import  com.sun.rowset.WebRowSetImpl;


public class CClientHTTP {

	static String _Protocol = "http://";
	static String _IP = "192.168.2.134";
	static String _Port = "8080";
	static String _Path = "/DBServices";
	static String _ResponseFormat = "JAVA-XML-WEBROWSET";
	static String _ResponseFormatVersion = "1.0";
	
    public final static String getJarFolder() {

		String name = CClientHTTP.class.getCanonicalName().replace( '.', '/' );

        String s = CClientHTTP.class.getClass().getResource( "/" + name + ".class" ).toString();

        s = s.replace( '/', File.separatorChar );

        if ( s.indexOf(".jar") >= 0 )
           s = s.substring( 0, s.indexOf(".jar") + 4 );
        else
           s = s.substring( 0, s.indexOf(".class") );

        if ( s.indexOf( "jar:file:\\" )  == 0 ) { //Windows style path SO inside jar file 

        	s = s.substring( 10 );

        }
        else if ( s.indexOf( "file:\\" )  == 0 ) { //Windows style path SO .class file

        	s = s.substring( 6 );

        }
        else { //Unix family ( Linux/BSD/Mac/Solaris ) style path SO

            s = s.substring( s.lastIndexOf(':') + 1 );

        }

        return s.substring( 0, s.lastIndexOf( File.separatorChar ) + 1 );

    }
	
    static CloseableHttpResponse CallServiceSystemEnumServices( CloseableHttpClient HTTPClient, String strURL, long lngPing ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Enum.Services" ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }
    
    static CloseableHttpResponse CallServiceSystemPing( CloseableHttpClient HTTPClient, String strURL, long lngPing ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Ping" ) );
    		urlParameters.add( new BasicNameValuePair( "Ping", Long.toString( lngPing ) ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }

    static String ParsePong( String strFileName ) {
    	
    	String strResult = "";
    	
    	try {
		
    		WebRowSet WebRSRead = new WebRowSetImpl();
    		
    		String strResponsePath = getJarFolder() + strFileName; //"/System.Enum.Services.response";
    		
			java.io.FileInputStream iStream = new java.io.FileInputStream( strResponsePath );

			WebRSRead.setPageSize( 10 );  //Set the number of rows to be loads into memory
			
			WebRSRead.readXml( iStream );
			
			if ( WebRSRead.next() ) {

				/*ResultSetMetaData MD = WebRSRead.getMetaData();
				
				for ( int I = 1; I <= MD.getColumnCount(); I++ ) {
					
					System.out.println( MD.getColumnLabel( I ) );					
					
				}*/
				
				strResult = WebRSRead.getString( "Pong" );

			}
			
			WebRSRead.close();
			
			iStream.close();
    		
		} 
    	catch ( Exception Ex ) {

    		Ex.printStackTrace();
    		
		} 
    	
    	return strResult;
    	
    }
    
    
    static CloseableHttpResponse CallServiceSystemStartSession( CloseableHttpClient HTTPClient, String strURL ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Start.Session" ) );
    		urlParameters.add( new BasicNameValuePair( "DBConnection", "DB1" ) );
    		urlParameters.add( new BasicNameValuePair( "username", "test1" ) );
    		urlParameters.add( new BasicNameValuePair( "password", "123qwerty" ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }
    
    static CloseableHttpResponse CallServiceSystemEndSession( CloseableHttpClient HTTPClient, String strURL, String strSecurityTokenID ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.End.Session" ) );
    		urlParameters.add( new BasicNameValuePair( "SecurityTokenID", strSecurityTokenID ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }
    
    static CloseableHttpResponse CallServiceSystemStartTransaction( CloseableHttpClient HTTPClient, String strURL, String strSecurityTokenID ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Start.Transaction" ) );
    		urlParameters.add( new BasicNameValuePair( "SecurityTokenID", strSecurityTokenID ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }

    static CloseableHttpResponse CallServiceSystemCommitTransaction( CloseableHttpClient HTTPClient, String strURL, String strSecurityTokenID, String strTransactionID ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Commit.Transaction" ) );
    		urlParameters.add( new BasicNameValuePair( "SecurityTokenID", strSecurityTokenID ) );
    		urlParameters.add( new BasicNameValuePair( "TransactionID", strTransactionID ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }

    static CloseableHttpResponse CallServiceSystemRollbackTransaction( CloseableHttpClient HTTPClient, String strURL, String strSecurityTokenID, String strTransactionID ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Rollback.Transaction" ) );
    		urlParameters.add( new BasicNameValuePair( "SecurityTokenID", strSecurityTokenID ) );
    		urlParameters.add( new BasicNameValuePair( "TransactionID", strTransactionID ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }
    
    static CloseableHttpResponse CallServiceSystemEndTransaction( CloseableHttpClient HTTPClient, String strURL, String strSecurityTokenID, String strTransactionID ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		//HTTPClient.getConnectionManager().
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.End.Transaction" ) );
    		urlParameters.add( new BasicNameValuePair( "SecurityTokenID", strSecurityTokenID ) );
    		urlParameters.add( new BasicNameValuePair( "TransactionID", strTransactionID ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) );
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }
    
    static CloseableHttpResponse CallServiceSystemExecuteSQL( CloseableHttpClient HTTPClient, String strURL, String strSecurityTokenID, String strTransactionID, String strSQL ) {
    	
    	CloseableHttpResponse Response = null;
    	
    	try {
    		
    		HttpPost PostData = new HttpPost( strURL );
    		 
    		// add header
    		PostData.setHeader("User-Agent", "Test-Services-Client" );
    	 
    		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
    		urlParameters.add( new BasicNameValuePair( "ServiceName", "System.Execute.SQL" ) );
    		urlParameters.add( new BasicNameValuePair( "SecurityTokenID", strSecurityTokenID ) );
    		urlParameters.add( new BasicNameValuePair( "TransactionID", strTransactionID ) );
    		//urlParameters.add( new BasicNameValuePair( "Commit", "1" ) );
    		urlParameters.add( new BasicNameValuePair( "InternalFetchSize", "100000" ) ); //Fetch internal sesult set to 100000 row, this better performance but use more memory ram on server 
    		urlParameters.add( new BasicNameValuePair( "SQL", strSQL ) );
    		urlParameters.add( new BasicNameValuePair( "ResponseFormat", _ResponseFormat ) ); //"CSV" )  ); //
    		urlParameters.add( new BasicNameValuePair( "ResponseFormatVersion", _ResponseFormatVersion ) ); //"1.0" ) ); //
    		
			PostData.setEntity( new UrlEncodedFormEntity( urlParameters ) );
			
			Response = HTTPClient.execute( PostData );
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
    	
    	return Response;
    	
    }

    static boolean SaveResponseToFile( InputStream IStream, String strFileName ) {
    	
    	boolean bResult = false;
    	
    	try {
    	
    		String strResponsePath = getJarFolder() + strFileName; //"/System.Enum.Services.response";

    		FileOutputStream OutputStream = new FileOutputStream( new File( strResponsePath ) );

    		int intChunkSize = 10240; //10kb

    		byte[] bytBuffer = new byte[ intChunkSize ];

    		int n = 0;
    		
    		while ( -1 != ( n = IStream.read( bytBuffer ) ) ) {

    			OutputStream.write( bytBuffer, 0, n );

    		}			

    		OutputStream.close();
    	
    		IStream.close();
    		
    		bResult = true;
    		
    	}
    	catch ( Exception Ex ) {
    		
    		Ex.printStackTrace();
    		
    	}
		
    	return bResult;
    	
    	
    }
    
    static String ParseSecurityTokenID( String strFileName ) {
    	
    	String strResult = "";
    	
    	try {
		
    		WebRowSet WebRSRead = new WebRowSetImpl();
    		
    		String strResponsePath = getJarFolder() + strFileName; //"/System.Enum.Services.response";
    		
			java.io.FileInputStream iStream = new java.io.FileInputStream( strResponsePath );

			WebRSRead.setPageSize( 10 );  //Set the number of rows to be loads into memory
			
			WebRSRead.readXml( iStream );
			
			if ( WebRSRead.next() ) {

				/*ResultSetMetaData MD = WebRSRead.getMetaData();
				
				for ( int I = 1; I <= MD.getColumnCount(); I++ ) {
					
					System.out.println( MD.getColumnLabel( I ) );					
					
				}*/
				
				strResult = WebRSRead.getString( "SecurityTokenID" );

			}
			
			WebRSRead.close();
			
			iStream.close();
    		
		} 
    	catch ( Exception Ex ) {

    		Ex.printStackTrace();
    		
		} 
    	
    	return strResult;
    	
    }
    
    static String ParseTransactionID( String strFileName ) {
    	
    	String strResult = "";
    	
    	try {
		
    		WebRowSet WebRSRead = new WebRowSetImpl();
    		
    		String strResponsePath = getJarFolder() + strFileName; //"/System.Enum.Services.response";
    		
			java.io.FileInputStream iStream = new java.io.FileInputStream( strResponsePath );

			WebRSRead.setPageSize( 10 );  //Set the number of rows to be loads into memory
			
			WebRSRead.readXml( iStream );
			
			if ( WebRSRead.next() ) {

				/*ResultSetMetaData MD = WebRSRead.getMetaData();
				
				for ( int I = 1; I <= MD.getColumnCount(); I++ ) {
					
					System.out.println( MD.getColumnLabel( I ) );					
					
				}*/
				
				strResult = WebRSRead.getString( "TransactionID" );

			}
			
			WebRSRead.close();
			
			iStream.close();
    		
		} 
    	catch ( Exception Ex ) {

    		Ex.printStackTrace();
    		
		} 
    	
    	return strResult;
    	
    }

    /**
	 * @param args
	 */
	public static void main(String[] args) {

		//System.setProperty( "org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog" );
        //System.setProperty( "org.apache.commons.logging.simplelog.showdatetime", "true" );
        //System.setProperty( "org.apache.commons.logging.simplelog.log.org.apache.http", "DEBUG" );
		
		String strURL = _Protocol + _IP + ":" + _Port + _Path;
		
		CloseableHttpClient HTTPClient = HttpClients.createDefault();
		
		//Call to System.Ping service for test 4 times
		
		try {
		
			int intPing = 100;
			int intPingCount = intPing + 6;
			
			//for ( long intCallService = 0; intCallService < intPingCount; intCallService++ ) {
			while ( intPing <= intPingCount ) {

				CloseableHttpResponse Response = CallServiceSystemPing( HTTPClient, strURL, intPing );

				if ( Response != null && SaveResponseToFile( Response.getEntity().getContent(), "System.Ping." + Long.toString( intPing ) + ".response" ) ) {

					System.out.println( "Ping => " + Long.toString( intPing ) );
					
					intPing = Integer.parseInt( ParsePong( "System.Ping." + Long.toString( intPing ) + ".response" ) );
					
					System.out.println( "Pong <= " + intPing );
					
				}

				intPing += 1;
				
			}
		
		}
		catch ( Exception Ex ) {
			
			Ex.printStackTrace();
			
		}
		
		try {
			
			CloseableHttpResponse Response = CallServiceSystemStartSession( HTTPClient, strURL );
			
			if ( Response != null && SaveResponseToFile( Response.getEntity().getContent(), "System.Start.Session.response" ) ) {
				
				Response.close();
				
				String strSecurityTokenID = ParseSecurityTokenID( "System.Start.Session.response" );
				
				if ( strSecurityTokenID.isEmpty() == false ) {

					System.out.println( "SecurityTokenID => " + strSecurityTokenID );
					
					Response = CallServiceSystemStartTransaction( HTTPClient, strURL, strSecurityTokenID );
					
					if ( Response != null && SaveResponseToFile( Response.getEntity().getContent(), "System.Start.Transaction.response" ) ) {
						
						Response.close();

						String strTransactionID = ParseTransactionID( "System.Start.Transaction.response" );
						
						if ( strTransactionID.isEmpty() == false ) {
							
							System.out.println( "TransactionID => " + strTransactionID );
							
							Response = CallServiceSystemExecuteSQL( HTTPClient, strURL, strSecurityTokenID, strTransactionID, "Select * From tblBigData" );
							
							if ( Response != null && SaveResponseToFile( Response.getEntity().getContent(), "System.Execute.SQL.1.response" ) ) {

								Response.close();
								
								System.out.println( "Sucess execute SQL query 1" );

								Response = CallServiceSystemExecuteSQL( HTTPClient, strURL, strSecurityTokenID, strTransactionID, "Select * From tblUsersDB Where IdUser=1" );
								
								if ( Response != null ) {
									
									SaveResponseToFile( Response.getEntity().getContent(), "System.Execute.SQL.2.response" );

									Response.close();
									
								}
								
								System.out.println( "Sucess execute SQL query 2" );
								
								Response = CallServiceSystemEndTransaction( HTTPClient, strURL, strSecurityTokenID, strTransactionID );

								if ( Response != null ) { 
									
									SaveResponseToFile( Response.getEntity().getContent(), "System.End.Transaction.response" );

									Response.close();
									
								};	
								
							}	
							
						}
						
					}
					
					Response = CallServiceSystemEndSession( HTTPClient, strURL, strSecurityTokenID );
					
					if ( Response != null ) { 
                        
						SaveResponseToFile( Response.getEntity().getContent(), "System.End.Session.response" );
						
						Response.close();
						
					}
					
				}
				
			}
			
		} 
		catch ( Exception Ex ) {

			Ex.printStackTrace();
		
		}

		try {

			HTTPClient.close();
			
		}
		catch ( Exception Ex ) {

			Ex.printStackTrace();
			
		}

		//client.close();
		/*HttpRequest httpRequest = HttpRequest.post( strURL );
		httpRequest.form( "ServiceName", "System.Enum.Services", "ResponseFormat", _ResponseFormat, "ResponseFormatVersion", _ResponseFormatVersion );	
		//httpRequest.queryEncoding( "UTF-8" );
		
		HttpResponse httpResponse = httpRequest.send();
		
		String strResponsePath = getJarFolder() + "/System.Enum.Services.response";
		
		FileOutputStream OStream;
		
		try {

			OStream = new FileOutputStream( new File( strResponsePath ) );
			
			System.out.println( httpResponse.header( "Content-Type" ) );
			
			httpResponse.sendTo( OStream );
			
		} catch ( Exception Ex ) {

			Ex.printStackTrace();
			
		}*/
		
		//HttpBrowser browser = new HttpBrowser();
		
		//browser.
		
		//httpResponse.readFrom(arg0)dFrom( IStream );
		
		//System.out.println( httpResponse.bodyText() );
		
	}

}
