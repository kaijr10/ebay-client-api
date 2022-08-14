"""
The small utility script to get access token for ebay account in sanbox environment.
The flow for get access token, link: 
https://developer.ebay.com/api-docs/static/oauth-client-credentials-grant.html
"""
import requests
import base64


CLIENT_ID = "PhanKhan-testapp-SBX-190eb715c-ccabcbb1"
CLIENT_SERCET = "SBX-90eb715cfb3f-8b51-4557-9ea0-fdee"
# Note for production environment, the URL will be https://api.ebay.com/identity/v1/oauth2/token
OAUTH2_TOKEN_URL = "https://api.sandbox.ebay.com/identity/v1/oauth2/token"
GRANT_TYPE = "client_credentials"
#SCOPE = "https://api.ebay.com/oauth/api_scope%20https://api.ebay.com/oauth/api_scope/buy.order.readonly%20https://api.ebay.com/oauth/api_scope/sell.inventory"
SCOPE = "https://api.ebay.com/oauth/api_scope/sell.inventory"
'''
SCOPE = [
    "https://api.ebay.com/oauth/api_scope",
    "https://api.ebay.com/oauth/api_scope/sell.marketing",
    "https://api.ebay.com/oauth/api_scope/sell.inventory",
    "https://api.ebay.com/oauth/api_scope/sell.fulfillment",
    "https://api.ebay.com/oauth/api_scope/sell.item",
    "https://api.ebay.com/oauth/api_scope/buy.order.readonly"

]
'''
def get_access_token():

    headers = {
        "Content-Type": "application/x-www-form-urlencoded",
        "Authorization": "Basic " + base64_encoded()
    }
    #print(requests.utils.quote(SCOPE, safe=''))
    body = {
        "grant_type": GRANT_TYPE,
        "scope": SCOPE
    }

    response = requests.post(OAUTH2_TOKEN_URL, headers=headers, data=body)

    print((response.status_code, response.text))

def base64_encoded():
    value = CLIENT_ID + ":" + CLIENT_SERCET
    value_byte = value.encode('ascii')
    b64_byte = base64.b64encode(value_byte)

    return b64_byte.decode('ascii')

if __name__ == '__main__':
    get_access_token()